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
import com.valaphee.netcode.mcje.network.PacketReader
import com.valaphee.netcode.mcje.network.ServerPlayPacketHandler
import com.valaphee.netcode.util.safeList
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer

/**
 * @author Kevin Ludwig
 */
class ServerTabCompletePacket(
    val id: Int,
    val start: Int,
    val length: Int,
    val matches: List<Match>
) : Packet<ServerPlayPacketHandler> {
    class Match(
        val match: String,
        val tooltip: Component?
    )

    override fun write(buffer: PacketBuffer, version: Int) {
        buffer.writeVarInt(id)
        buffer.writeVarInt(start)
        buffer.writeVarInt(length)
        buffer.writeVarInt(matches.size)
        matches.forEach {
            buffer.writeString(it.match)
            it.tooltip?.let {
                buffer.writeBoolean(true)
                buffer.writeString(GsonComponentSerializer.gson().serialize(it))
            } ?: buffer.writeBoolean(false)
        }
    }

    override fun handle(handler: ServerPlayPacketHandler) = handler.tabComplete(this)

    override fun toString() = "ServerTabCompletePacket(id=$id, start=$start, length=$length, matches=$matches)"
}

/**
 * @author Kevin Ludwig
 */
object ServerTabCompletePacketReader : PacketReader {
    override fun read(buffer: PacketBuffer, version: Int) = ServerTabCompletePacket(buffer.readVarInt(), buffer.readVarInt(), buffer.readVarInt(), safeList(buffer.readVarInt()) { ServerTabCompletePacket.Match(buffer.readString(), if (buffer.readBoolean()) GsonComponentSerializer.gson().deserialize(buffer.readString()) else null) })
}
