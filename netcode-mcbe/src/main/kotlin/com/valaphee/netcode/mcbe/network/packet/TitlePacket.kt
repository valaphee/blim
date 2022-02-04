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
@Restrict(Restriction.ToClient)
class TitlePacket(
    val action: Action,
    val text: String,
    val fadeInTime: Int,
    val stayTime: Int,
    val fadeOutTime: Int,
    val xboxUserId: String,
    val platformChatId: String
) : Packet() {
    enum class Action {
        ClearTitle, ResetTitle, SetTitle, SetSubTitle, SetActionBarMessage, SetTimings
    }

    override val id get() = 0x58

    override fun write(buffer: PacketBuffer, version: Int) {
        buffer.writeVarInt(action.ordinal)
        buffer.writeString(text)
        buffer.writeVarInt(fadeInTime)
        buffer.writeVarInt(stayTime)
        buffer.writeVarInt(fadeOutTime)
        buffer.writeString(xboxUserId)
        buffer.writeString(platformChatId)
    }

    override fun handle(handler: PacketHandler) = handler.title(this)

    override fun toString() = "TitlePacket(action=$action, text='$text', fadeInTime=$fadeInTime, stayTime=$stayTime, fadeOutTime=$fadeOutTime, xboxUserId='$xboxUserId', platformChatId='$platformChatId')"
}

/**
 * @author Kevin Ludwig
 */
object TitlePacketReader : PacketReader {
    override fun read(buffer: PacketBuffer, version: Int) = TitlePacket(
        TitlePacket.Action.values()[buffer.readVarInt()],
        buffer.readString(),
        buffer.readVarInt(),
        buffer.readVarInt(),
        buffer.readVarInt(),
        buffer.readString(),
        buffer.readString()
    )
}
