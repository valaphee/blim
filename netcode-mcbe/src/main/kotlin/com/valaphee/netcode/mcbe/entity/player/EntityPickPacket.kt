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

package com.valaphee.netcode.mcbe.entity.player

import com.valaphee.netcode.mcbe.Packet
import com.valaphee.netcode.mcbe.PacketBuffer
import com.valaphee.netcode.mcbe.PacketHandler
import com.valaphee.netcode.mcbe.PacketReader
import com.valaphee.netcode.mcbe.Restrict
import com.valaphee.netcode.mcbe.Restriction

/**
 * @author Kevin Ludwig
 */
@Restrict(Restriction.ToServer)
class EntityPickPacket(
    val runtimeEntityId: Long,
    val hotbarSlot: Int,
    val withMetadata: Boolean
) : Packet() {
    override val id get() = 0x23

    override fun write(buffer: PacketBuffer, version: Int) {
        buffer.writeLongLE(runtimeEntityId)
        buffer.writeByte(hotbarSlot)
        if (version >= 465) buffer.writeBoolean(withMetadata)
    }

    override fun handle(handler: PacketHandler) = handler.entityPick(this)

    override fun toString() = "EntityPickPacket(runtimeEntityId=$runtimeEntityId, hotbarSlot=$hotbarSlot, withMetadata=$withMetadata)"
}

/**
 * @author Kevin Ludwig
 */
object EntityPickPacketReader : PacketReader {
    override fun read(buffer: PacketBuffer, version: Int) = EntityPickPacket(buffer.readLongLE(), buffer.readByte().toInt(), if (version >= 465) buffer.readBoolean() else false)
}
