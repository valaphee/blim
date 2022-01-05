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

package com.valaphee.netcode.mcbe.entity

import com.valaphee.foundry.math.Float3
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
class ExperienceOrbAddPacket(
    val position: Float3,
    val amount: Int
) : Packet() {
    override val id get() = 0x42

    override fun write(buffer: PacketBuffer, version: Int) {
        buffer.writeFloat3(position)
        buffer.writeVarInt(amount)
    }

    override fun handle(handler: PacketHandler) = handler.experienceOrbAdd(this)

    override fun toString() = "ExperienceOrbAddPacket(position=$position, amount=$amount)"
}

/**
 * @author Kevin Ludwig
 */
object ExperienceOrbAddPacketReader : PacketReader {
    override fun read(buffer: PacketBuffer, version: Int) = ExperienceOrbAddPacket(buffer.readFloat3(), buffer.readVarInt())
}