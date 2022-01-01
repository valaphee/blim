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

package com.valaphee.netcode.mcbe.world

import com.valaphee.foundry.math.Float3
import com.valaphee.netcode.mcbe.Packet
import com.valaphee.netcode.mcbe.PacketBuffer
import com.valaphee.netcode.mcbe.PacketHandler
import com.valaphee.netcode.mcbe.PacketReader

/**
 * @author Kevin Ludwig
 */
class SoundEventPacketV1(
    val soundEvent: SoundEvent,
    val position: Float3,
    val extraData: Int,
    val pitch: Int,
    val babySound: Boolean,
    val relativeVolumeDisabled: Boolean
) : Packet() {
    override val id get() = 0x18

    override fun write(buffer: PacketBuffer, version: Int) {
        buffer.writeByte(SoundEvent.registry.getId(soundEvent))
        buffer.writeFloat3(position)
        buffer.writeVarInt(extraData)
        buffer.writeVarInt(pitch)
        buffer.writeBoolean(babySound)
        buffer.writeBoolean(relativeVolumeDisabled)
    }

    override fun handle(handler: PacketHandler) = handler.soundEventV1(this)

    override fun toString() = "SoundEventPacketV1(soundEvent=$soundEvent, position=$position, extraData=$extraData, pitch=$pitch, babySound=$babySound, relativeVolumeDisabled=$relativeVolumeDisabled)"
}

/**
 * @author Kevin Ludwig
 */
object SoundEventPacketV1Reader : PacketReader {
    override fun read(buffer: PacketBuffer, version: Int) = SoundEventPacketV1(checkNotNull(SoundEvent.registry[buffer.readUnsignedByte().toInt()]), buffer.readFloat3(), buffer.readVarInt(), buffer.readVarInt(), buffer.readBoolean(), buffer.readBoolean())
}
