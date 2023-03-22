package com.next.goldentime.data.fixtures

import com.next.goldentime.repository.location.Location
import com.next.goldentime.repository.sos.SOSInfo
import com.next.goldentime.repository.sos.SOSState

val sosInfoA = SOSInfo(profileA, Location(37.5601985, 126.9369965))

val sosInfoB = SOSInfo(profileB, Location(37.5617343, 126.9367201))

val sosStateA = SOSState(0, -1.0, false)

val sosStateB = SOSState(1, 100.0, false)

val sosStateC = SOSState(4, 67.5, false)

val sosStateD = SOSState(4, 25.1, false)

val sosStateE = SOSState(5, 0.0, false)

val sosStateF = SOSState(5, 0.0, true)