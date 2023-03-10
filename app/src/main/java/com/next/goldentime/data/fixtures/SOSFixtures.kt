package com.next.goldentime.data.fixtures

import com.next.goldentime.repository.sos.Location
import com.next.goldentime.repository.sos.SOS
import com.next.goldentime.repository.sos.SOSState

val sosA = SOS(userA, Location(0.0, 0.0))

val sosB = SOS(userB, Location(0.0, 0.0))

val sosStateA = SOSState(3, 10.0, false)

val sosStateB = SOSState(5, 3.0, false)