/*
 * Copyright 2023. CatMoe / FallenCrystal
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
 *
 */

package catmoe.fallencrystal.moefilter.util.message.v2.packet

import catmoe.fallencrystal.moefilter.util.message.v2.packet.type.MessagesType
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.protocol.ProtocolConstants
import net.md_5.bungee.protocol.packet.Chat
import net.md_5.bungee.protocol.packet.SystemChat
import net.md_5.bungee.protocol.packet.Title

class ViaActionbarPacket(
    val v119: SystemChat?,
    val v117: Chat?,
    val v111: Title?,
    val v110: Chat?,
    val has119Data: Boolean,
    val has117Data: Boolean,
    val has111Data: Boolean,
    val has110Data: Boolean,
    val bc: BaseComponent,
    val cs: String,
    val originalMessage: String,
) : MessagePacket {
    override fun getType(): MessagesType { return MessagesType.ACTION_BAR }

    override fun supportChecker(version: Int): Boolean {
        if (has119Data && version >= ProtocolConstants.MINECRAFT_1_19) return true
        if (has117Data && version > ProtocolConstants.MINECRAFT_1_17) return true
        if (has111Data && version > ProtocolConstants.MINECRAFT_1_10) return true
        return has110Data && version > ProtocolConstants.MINECRAFT_1_8
    }

    override fun getBaseComponent(): BaseComponent { return bc }

    override fun getComponentSerializer(): String { return cs }

    override fun getOriginal(): String { return originalMessage }
}