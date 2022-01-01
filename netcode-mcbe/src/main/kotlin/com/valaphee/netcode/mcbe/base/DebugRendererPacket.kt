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

import com.valaphee.foundry.math.Float3
import com.valaphee.foundry.math.Float4
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
class DebugRendererPacket(
    val type: Type,
    val markerText: String?,
    val markerPosition: Float3?,
    val markerColor: Float4?,
    val markerDuration: Long
) : Packet() {
    enum class Type {
        None,
        ClearDebugMarkers,
        AddDebugMarkerCube
    }

    override val id get() = 0xA4

    override fun write(buffer: PacketBuffer, version: Int) {
        buffer.writeIntLE(type.ordinal)
        if (type == Type.AddDebugMarkerCube) {
            buffer.writeString(markerText!!)
            buffer.writeFloat3(markerPosition!!)
            val (markerColorRed, markerColorGreen, markerColorBlue, markerColorAlpha) = markerColor!!
            buffer.writeFloatLE(markerColorRed)
            buffer.writeFloatLE(markerColorGreen)
            buffer.writeFloatLE(markerColorBlue)
            buffer.writeFloatLE(markerColorAlpha)
            buffer.writeLong(markerDuration)
        }
    }

    override fun handle(handler: PacketHandler) = handler.debugRenderer(this)

    override fun toString() = "DebugRendererPacket(type=$type, markerText=$markerText, markerPosition=$markerPosition, markerColor=$markerColor, markerDuration=$markerDuration)"
}

/**
 * @author Kevin Ludwig
 */
object DebugRendererPacketReader : PacketReader {
    override fun read(buffer: PacketBuffer, version: Int): DebugRendererPacket {
        val type = DebugRendererPacket.Type.values()[buffer.readIntLE()]
        val markerText: String?
        val markerPosition: Float3?
        val markerColor: Float4?
        val markerDuration: Long
        if (type == DebugRendererPacket.Type.AddDebugMarkerCube) {
            markerText = buffer.readString()
            markerPosition = buffer.readFloat3()
            markerColor = Float4(buffer.readFloatLE(), buffer.readFloatLE(), buffer.readFloatLE(), buffer.readFloatLE())
            markerDuration = buffer.readLong()
        } else {
            markerText = null
            markerPosition = null
            markerColor = null
            markerDuration = 0
        }
        return DebugRendererPacket(type, markerText, markerPosition, markerColor, markerDuration)
    }
}
