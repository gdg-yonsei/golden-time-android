package com.next.goldentime.data.fixtures

import com.next.goldentime.repository.sos.Location
import com.next.goldentime.repository.sos.SOS
import com.next.goldentime.repository.sos.SOSState

val sosA = SOS(userA, Location(0.0, 0.0))

val sosB = SOS(userB, Location(0.0, 0.0))

val sosStateA = SOSState(0, -1.0, false)

val sosStateB = SOSState(1, 300.0, false)

val sosStateC = SOSState(4, 150.0, false)

val sosStateD = SOSState(4, 3.0, false)

val sosStateE = SOSState(5, 0.0, false)

val sosStateF = SOSState(5, 0.0, true)