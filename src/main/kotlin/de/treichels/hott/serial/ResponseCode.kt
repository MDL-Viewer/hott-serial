/**
 * HoTT Transmitter Config Copyright (C) 2013 Oliver Treichel
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http:></http:>//www.gnu.org/licenses/>.
 */
package de.treichels.hott.serial

import java.util.stream.Stream

enum class ResponseCode(val id: Int) {
    ACK(1), NACK(2), ERROR(3), CRC_ERROR(4), BUSY(5), Unknown(-1);

    companion object {
        fun forId(id: Int): ResponseCode {
            return Stream.of(*values()).filter { r -> r.id == id }.findFirst().orElse(Unknown)
        }
    }
}