/* This program is free software: you can redistribute it and/or modify
*  it under the terms of the GNU General Public License as published by
*  the Free Software Foundation, either version 3 of the License, or
*  (at your option) any later version.
*
*  This program is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*  GNU General Public License for more details.
*
*  You should have received a copy of the GNU General Public License
*  along with this program.  If not, see <http://www.gnu.org/licenses/>.
*
*  <jalucenyo@gmail.com> 13 of August, 2015
*  Jose A. Luceño
*/
package com.jalcdeveloper.weaconapp.communication;

import com.jalcdeveloper.weaconapp.BuildConfig;

public class PubNubHelper  {

    public static final String PUBLISH_KEY = BuildConfig.PUBNUB_PUBLISH_KEY;
    public static final String SUSCRIBE_KEY = BuildConfig.PUBNUB_SUBSCRIBE_KEY;
    public static final String SECRET_KEY = BuildConfig.PUBNUB_SECRET_KEY;
    public static final String CHANNEL_WEACON_LIST = "sensors_list";

}
