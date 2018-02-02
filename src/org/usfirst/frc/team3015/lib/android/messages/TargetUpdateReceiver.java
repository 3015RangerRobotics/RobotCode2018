package org.usfirst.frc.team3015.lib.android.messages;

import org.usfirst.frc.team3015.lib.android.TargetUpdate;

public interface TargetUpdateReceiver {
    void onUpdateReceived(TargetUpdate update);
}
