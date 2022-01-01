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

import com.valaphee.netcode.mcbe.Packet
import com.valaphee.netcode.mcbe.PacketBuffer
import com.valaphee.netcode.mcbe.PacketHandler
import com.valaphee.netcode.mcbe.PacketReader

/**
 * @author Kevin Ludwig
 */
class SimpleEventPacket(
    val event: Event
) : Packet() {
    enum class Event {
        EnableCommands, DisableCommands, UnlockWorldTemplateSettings
    }

    override val id get() = 0x40

    override fun write(buffer: PacketBuffer, version: Int) {
        buffer.writeShortLE(event.ordinal)
    }

    override fun handle(handler: PacketHandler) = handler.simpleEvent(this)

    override fun toString() = "SimpleEventPacket(event=$event)"
}

/**
 * @author Kevin Ludwig
 */
object SimpleEventPacketReader : PacketReader {
    override fun read(buffer: PacketBuffer, version: Int) = SimpleEventPacket(SimpleEventPacket.Event.values()[buffer.readShortLE().toInt()])
}
