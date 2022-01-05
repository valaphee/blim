/*
 * Copyright (c) 2021-2022, Valaphee.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.valaphee.netcode.mcje.play

import com.fasterxml.jackson.module.kotlin.readValue
import com.valaphee.netcode.mcje.Packet
import com.valaphee.netcode.mcje.PacketBuffer
import com.valaphee.netcode.mcje.PacketReader
import com.valaphee.netcode.mcje.util.text.Component
import com.valaphee.netcode.util.ByteBufStringReader

/**
 * @author Kevin Ludwig
 */
class ServerPlayerCombatEventPacket(
    val event: Event,
    val durationOrPlayerEntityId: Int,
    val entityId: Int,
    val message: Component?
) : Packet<ServerPlayPacketHandler> {
    enum class Event {
        Enter, End, Death
    }

    override fun write(buffer: PacketBuffer, version: Int) {
        buffer.writeVarInt(event.ordinal)
        @Suppress("NON_EXHAUSTIVE_WHEN") when (event) {
            Event.End -> {
                buffer.writeVarInt(durationOrPlayerEntityId)
                buffer.writeInt(entityId)
            }
            Event.Death -> {
                buffer.writeVarInt(durationOrPlayerEntityId)
                buffer.writeInt(entityId)
                buffer.writeString(buffer.objectMapper.writeValueAsString(message))
            }
        }
    }

    override fun handle(handler: ServerPlayPacketHandler) = handler.playerCombatEvent(this)

    override fun toString() = "ServerPlayerCombatEventPacket(event=$event, durationOrPlayerEntityId=$durationOrPlayerEntityId, entityId=$entityId, message=$message)"
}

/**
 * @author Kevin Ludwig
 */
object ServerPlayerCombatEventPacketReader : PacketReader {
    override fun read(buffer: PacketBuffer, version: Int): ServerPlayerCombatEventPacket {
        val event = ServerPlayerCombatEventPacket.Event.values()[buffer.readVarInt()]
        val durationOrPlayerEntityId: Int
        val entityId: Int
        val message: Component?
        @Suppress("NON_EXHAUSTIVE_WHEN") when (event) {
            ServerPlayerCombatEventPacket.Event.End -> {
                durationOrPlayerEntityId = buffer.readVarInt()
                entityId = buffer.readInt()
                message = null
            }
            ServerPlayerCombatEventPacket.Event.Death -> {
                durationOrPlayerEntityId = buffer.readVarInt()
                entityId = buffer.readInt()
                message = buffer.objectMapper.readValue(ByteBufStringReader(buffer, buffer.readVarInt()))
            }
            else -> {
                durationOrPlayerEntityId = 0
                entityId = 0
                message = null
            }
        }
        return ServerPlayerCombatEventPacket(event, durationOrPlayerEntityId, entityId, message)
    }
}