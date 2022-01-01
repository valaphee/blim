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
import com.valaphee.netcode.mcje.util.NamespacedKey

/**
 * @author Kevin Ludwig
 */
class ClientAdvancementTabPacket(
    val action: Action,
    val tabId: NamespacedKey,
) : Packet<ClientPlayPacketHandler> {
    enum class Action {
        Opened, Closed
    }

    override fun write(buffer: PacketBuffer, version: Int) {
        buffer.writeVarInt(action.ordinal)
        buffer.writeNamespacedKey(tabId)
    }

    override fun handle(handler: ClientPlayPacketHandler) = handler.advancementTab(this)

    override fun toString() = "ClientAdvancementTabPacket(action=$action, tabId=$tabId)"
}

/**
 * @author Kevin Ludwig
 */
object ClientAdvancementTabPacketReader : PacketReader {
    override fun read(buffer: PacketBuffer, version: Int) = ClientAdvancementTabPacket(ClientAdvancementTabPacket.Action.values()[buffer.readVarInt()], buffer.readNamespacedKey())
}
