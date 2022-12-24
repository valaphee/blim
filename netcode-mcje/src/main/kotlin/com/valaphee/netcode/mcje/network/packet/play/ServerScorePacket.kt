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

package com.valaphee.netcode.mcje.network.packet.play

import com.valaphee.netcode.mcje.network.Packet
import com.valaphee.netcode.mcje.network.PacketBuffer
import com.valaphee.netcode.mcje.network.ServerPlayPacketHandler

/**
 * @author Kevin Ludwig
 */
class ServerScorePacket(
    val name: String,
    val action: Action,
    val objectiveName: String,
    val value: Int
) : Packet<ServerPlayPacketHandler>() {
    enum class Action {
        Set, Remove
    }

    override fun write(buffer: PacketBuffer, version: Int) {
        buffer.writeString(name)
        buffer.writeByte(action.ordinal)
        buffer.writeString(objectiveName)
        if (action == Action.Set) buffer.writeVarInt(value)
    }

    override fun handle(handler: ServerPlayPacketHandler) = handler.score(this)

    override fun toString() = "ServerScorePacket(name='$name', action=$action, objectiveName='$objectiveName', value=$value)"

    object Reader : Packet.Reader {
        override fun read(buffer: PacketBuffer, version: Int): ServerScorePacket {
            val name = buffer.readString(40)
            val action = Action.values()[buffer.readUnsignedByte().toInt()]
            val objectiveName = buffer.readString(16)
            val value = if (action == Action.Set) buffer.readVarInt() else 0
            return ServerScorePacket(name, action, objectiveName, value)
        }
    }
}
