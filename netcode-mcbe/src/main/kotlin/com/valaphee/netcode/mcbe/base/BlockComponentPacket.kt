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

package com.valaphee.netcode.mcbe.base

import com.valaphee.netcode.mc.util.nbt.CompoundTag
import com.valaphee.netcode.mcbe.Packet
import com.valaphee.netcode.mcbe.PacketBuffer
import com.valaphee.netcode.mcbe.PacketHandler
import com.valaphee.netcode.mcbe.PacketReader
import com.valaphee.netcode.mcbe.Restrict
import com.valaphee.netcode.mcbe.Restriction

/**
 * @author Kevin Ludwig
 */
@Restrict(Restriction.ToClient)
class BlockComponentPacket(
    val tag: CompoundTag?
) : Packet() {
    override val id get() = 0x86

    override fun write(buffer: PacketBuffer, version: Int) {
        buffer.toNbtOutputStream().use { it.writeTag(tag) }
    }

    override fun handle(handler: PacketHandler) = handler.blockComponent(this)

    override fun toString() = "BlockComponentPacket(tag=$tag)"
}

/**
 * @author Kevin Ludwig
 */
object BlockComponentPacketReader : PacketReader {
    override fun read(buffer: PacketBuffer, version: Int) = BlockComponentPacket(buffer.toNbtInputStream().use { it.readTag()?.asCompoundTag() })
}
