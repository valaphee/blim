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
class ServerDisconnectPacket(
    val message: Component
) : Packet<ServerPlayPacketHandler> {
    override fun write(buffer: PacketBuffer, version: Int) {
        buffer.writeString(buffer.objectMapper.writeValueAsString(message))
    }

    override fun handle(handler: ServerPlayPacketHandler) = handler.disconnect(this)

    override fun toString() = "ServerDisconnectPacket(message=$message)"
}

/**
 * @author Kevin Ludwig
 */
object ServerDisconnectPacketReader : PacketReader {
    override fun read(buffer: PacketBuffer, version: Int) = ServerDisconnectPacket(buffer.objectMapper.readValue(ByteBufStringReader(buffer, buffer.readVarInt())))
}