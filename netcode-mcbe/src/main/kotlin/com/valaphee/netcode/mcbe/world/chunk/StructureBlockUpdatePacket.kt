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

package com.valaphee.netcode.mcbe.world.chunk

import com.valaphee.foundry.math.Int3
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
class StructureBlockUpdatePacket(
    val position: Int3,
    val name: String,
    val metadata: String,
    val includingPlayers: Boolean,
    val showBoundingBox: Boolean,
    val mode: Mode,
    val settings: StructureSettings,
    val redstoneSaveMode: RedstoneSaveMode,
    val powered: Boolean
) : Packet() {
    enum class Mode {
        Data, Save, Load, Corner, None, Export
    }

    enum class RedstoneSaveMode {
        SavesToMemory, SavesToDisk
    }

    override val id get() = 0x5A

    override fun write(buffer: PacketBuffer, version: Int) {
        buffer.writeInt3UnsignedY(position)
        buffer.writeString(name)
        buffer.writeString(metadata)
        buffer.writeBoolean(includingPlayers)
        buffer.writeBoolean(showBoundingBox)
        buffer.writeVarInt(mode.ordinal)
        if (version >= 440) buffer.writeStructureSettings(settings) else buffer.writeStructureSettingsPre440(settings)
        buffer.writeVarInt(redstoneSaveMode.ordinal)
        buffer.writeBoolean(powered)
    }

    override fun handle(handler: PacketHandler) = handler.structureBlockUpdate(this)

    override fun toString() = "StructureBlockUpdatePacket(position=$position, name='$name', metadata='$metadata', includingPlayers=$includingPlayers, showBoundingBox=$showBoundingBox, mode=$mode, settings=$settings, redstoneSaveMode=$redstoneSaveMode, powered=$powered)"
}

/**
 * @author Kevin Ludwig
 */
object StructureBlockUpdatePacketReader : PacketReader {
    override fun read(buffer: PacketBuffer, version: Int) = StructureBlockUpdatePacket(
        buffer.readInt3UnsignedY(),
        buffer.readString(),
        buffer.readString(),
        buffer.readBoolean(),
        buffer.readBoolean(),
        StructureBlockUpdatePacket.Mode.values()[buffer.readVarInt()],
        if (version >= 440) buffer.readStructureSettings() else buffer.readStructureSettingsPre440(),
        StructureBlockUpdatePacket.RedstoneSaveMode.values()[buffer.readVarInt()],
        buffer.readBoolean()
    )
}
