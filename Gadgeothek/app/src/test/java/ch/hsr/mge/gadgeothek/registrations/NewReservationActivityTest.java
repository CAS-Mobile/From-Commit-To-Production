package ch.hsr.mge.gadgeothek.registrations;

import org.junit.Ignore;
import org.junit.Test;

import ch.hsr.mge.gadgeothek.ui.reservations.NewReservationActivity;


public class NewReservationActivityTest {

    @Ignore("Demo for problems with unit tests using android framework classes")
    @Test
    public void testFinish(){
        NewReservationActivity activity = new NewReservationActivity();

        activity.finish();

        // some assertion to make sure no pending transactions exist
        // ...
    }


}
