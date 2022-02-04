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

package com.valaphee.netcode.mcbe.network.packet

import com.valaphee.netcode.mcbe.Restrict
import com.valaphee.netcode.mcbe.Restriction
import com.valaphee.netcode.mcbe.network.Packet
import com.valaphee.netcode.mcbe.network.PacketBuffer
import com.valaphee.netcode.mcbe.network.PacketHandler
import com.valaphee.netcode.mcbe.network.PacketReader

/**
 * @author Kevin Ludwig
 */
@Restrict(Restriction.ToServer)
class NpcRequestPacket(
    val runtimeEntityId: Long,
    val type: Type,
    val command: String,
    val actionType: Int,
    val scene: String
) : Packet() {
    enum class Type {
        SetAction, ExecuteCommandAction, ExecuteClosingCommands, SetName, SetSkin, SetInteractionText, ExecuteOpeningCommands
    }

    override val id get() = 0x62

    override fun write(buffer: PacketBuffer, version: Int) {
        buffer.writeVarULong(runtimeEntityId)
        buffer.writeByte(type.ordinal)
        buffer.writeString(command)
        buffer.writeByte(actionType)
        buffer.writeString(scene)
    }

    override fun handle(handler: PacketHandler) = handler.npcRequest(this)

    override fun toString() = "NpcRequestPacket(runtimeEntityId=$runtimeEntityId, type=$type, command='$command', actionType=$actionType, scene='$scene')"
}

/**
 * @author Kevin Ludwig
 */
object NpcRequestPacketReader : PacketReader {
    override fun read(buffer: PacketBuffer, version: Int) = NpcRequestPacket(
        buffer.readVarULong(),
        NpcRequestPacket.Type.values()[buffer.readUnsignedByte().toInt()],
        buffer.readString(),
        buffer.readUnsignedByte().toInt(),
        buffer.readString()
    )
}
