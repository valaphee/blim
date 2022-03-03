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
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer

/**
 * @author Kevin Ludwig
 */
class ServerPlayerListHeaderFooterPacket(
    val header: Component,
    val footer: Component
) : Packet<ServerPlayPacketHandler> {
    override fun write(buffer: PacketBuffer, version: Int) {
        buffer.writeString(GsonComponentSerializer.gson().serialize(header))
        buffer.writeString(GsonComponentSerializer.gson().serialize(footer))
    }

    override fun handle(handler: ServerPlayPacketHandler) = handler.playerListHeaderFooter(this)

    override fun toString() = "ServerPlayerListHeaderFooterPacket(header=$header, footer=$footer)"
}

/**
 * @author Kevin Ludwig
 */
object ServerPlayerListHeaderFooterPacketReader : PacketReader {
    override fun read(buffer: PacketBuffer, version: Int) = ServerPlayerListHeaderFooterPacket(GsonComponentSerializer.gson().deserialize(buffer.readString()), GsonComponentSerializer.gson().deserialize(buffer.readString()))
}
