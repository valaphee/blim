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

package com.valaphee.netcode.mcbe.world.map

/**
 * @author Kevin Ludwig
 */
data class Decoration(
    val type: Type,
    val rotation: Byte,
    val positionX: Byte,
    val positionY: Byte,
    val label: String,
    val color: Int
) {
    enum class Type {
        WhiteArrow,
        Unknown1,
        RedArrow,
        BlueArrow,
        TreasureMarker,
        RedPointer,
        WhiteCircle,
        GreenArrow,
        Unknown8,
        Unknown9,
        Unknown10,
        Unknown11,
        Unknown12,
        Banner,
        Mansion,
        Temple
    }
}
