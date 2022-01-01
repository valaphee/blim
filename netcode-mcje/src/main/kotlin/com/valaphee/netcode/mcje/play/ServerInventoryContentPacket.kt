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

import com.valaphee.netcode.mcje.Packet
import com.valaphee.netcode.mcje.PacketBuffer
import com.valaphee.netcode.mcje.PacketReader
import com.valaphee.netcode.mcje.item.stack.Stack
import com.valaphee.netcode.mcje.item.stack.readStack
import com.valaphee.netcode.mcje.item.stack.writeStack
import com.valaphee.netcode.util.safeList

/**
 * @author Kevin Ludwig
 */
class ServerInventoryContentPacket(
    val windowId: Int,
    val content: List<Stack?>
) : Packet<ServerPlayPacketHandler> {
    override fun write(buffer: PacketBuffer, version: Int) {
        buffer.writeByte(windowId)
        buffer.writeShort(content.size)
        content.forEach { buffer.writeStack(it) }
    }

    override fun handle(handler: ServerPlayPacketHandler) = handler.inventoryContent(this)

    override fun toString() = "ServerInventoryContentPacket(windowId=$windowId, content=$content)"
}

/**
 * @author Kevin Ludwig
 */
object ServerInventoryContentPacketReader : PacketReader {
    override fun read(buffer: PacketBuffer, version: Int) = ServerInventoryContentPacket(buffer.readUnsignedByte().toInt(), safeList(buffer.readShort().toInt()) { buffer.readStack() })
}
