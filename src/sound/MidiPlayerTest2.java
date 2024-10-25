package sound;

// Java program showing how to change the instrument type
import javax.sound.midi.*;
import java.util.*;

public class MidiPlayerTest2 {

    public static void main(String[] args)
    {

        MidiPlayerTest2 player = new MidiPlayerTest2();

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the instrument to be played");
        int instrument = in.nextInt();
        System.out.println("Enter the note to be played");
        int note = in.nextInt();

        player.setUpPlayer(instrument, note);
    }

    public void setUpPlayer(int instrument, int note)
    {

        try {

            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            Track track = sequence.createTrack();

            // Set the instrument type
            track.add(makeEvent(192, 1, instrument, 0, 1));

            // Add a note on event with specified note
            track.add(makeEvent(144, 1, note, 100, 1));

            // Add a note off event with specified note
            track.add(makeEvent(128, 1, note, 100, 4));

            sequencer.setSequence(sequence);
            sequencer.start();

            while (true) {

                if (!sequencer.isRunning()) {
                    sequencer.close();
                    System.exit(1);
                }
            }
        }
        catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public MidiEvent makeEvent(int command, int channel,
                               int note, int velocity, int tick)
    {

        MidiEvent event = null;

        try {

            ShortMessage a = new ShortMessage();
            a.setMessage(command, channel, note, velocity);

            event = new MidiEvent(a, tick);
        }
        catch (Exception ex) {

            ex.printStackTrace();
        }
        return event;
    }
}
