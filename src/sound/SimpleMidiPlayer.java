package sound;

import javax.sound.midi.*;

public class SimpleMidiPlayer {

    //Supposedly Middle C is 60, C# is 61, D is 62 etc...

    public static final int PLAY = ShortMessage.NOTE_ON;
    public static final int STOP = ShortMessage.NOTE_OFF;
    public static final int INSTRUMENT = ShortMessage.PROGRAM_CHANGE;
    public static final int DEFAULT_NOTE_LENGTH = 10;

    public int instrument;
    public Sequencer sequencer;

    private Sequence sequence;
    private Track track;

    /**
     * Constructor for SimpleMidiPlayer
     * @param instrument instrument code
     * @param timing in PPQ (Pulses per Quarter note)
     * @throws MidiUnavailableException
     * @throws InvalidMidiDataException
     */
    public SimpleMidiPlayer(int instrument,int timing) throws MidiUnavailableException, InvalidMidiDataException {
        this.instrument=instrument;
        try{
            this.sequencer= MidiSystem.getSequencer(); //Throws MidiUnavailableException if system MIDI player is unavailable
            sequencer.open();

            //PPQ resolution - Ticks per quarter note (PPQ - Pulse per quarter note)
            this.sequence = new Sequence(Sequence.PPQ, timing);  //Throws InvalidMidiDataException if first arg is invalid

            //Create new track. Add events to the track before starting the sequencer:
            this.track = sequence.createTrack();

        } catch (Exception e) {
            //Silently fail...(Please):
            System.out.println(e.toString());
        }
    }
    //Overload
    public void setInstrument(int channel, int instrument) throws InvalidMidiDataException {
        this.instrument = instrument;
        track.add(newEvent(INSTRUMENT,channel,instrument,0,0));
    }
    public void setInstrument(int channel, int instrument, int time) throws InvalidMidiDataException {
        this.instrument = instrument;
        track.add(newEvent(INSTRUMENT,channel,instrument,0,time));
    }

    //Overload
    public void addToTrack(int channel, int note,int time) throws InvalidMidiDataException {
        this.track.add(newEvent(PLAY,channel,note,100,time));
        this.track.add(newEvent(STOP,channel,note,100,time+DEFAULT_NOTE_LENGTH));
    }
    public void addToTrack(int channel, int note,int time, int length) throws InvalidMidiDataException {
        this.track.add(newEvent(PLAY,channel,note,100,time));
        this.track.add(newEvent(STOP,channel,note,100,time+length));
    }

    //Starts playing the sequence
    public void playTrack() throws InvalidMidiDataException {
        sequencer.setSequence(sequence);
        sequencer.start();
    }
    public void stopPlayback(){
        sequencer.stop();
    }





    private MidiEvent newEvent(int command, int channel,int note, int velocity, int tick) throws InvalidMidiDataException {
        MidiEvent event = null;

        try {
            //
            ShortMessage shortMessage = new ShortMessage(); //1-2 bytes
            shortMessage.setMessage(command, channel, note, velocity); //Throws InvalidMidiDataException if any part of the data is invalid

            event = new MidiEvent(shortMessage, tick);
        }
        catch (Exception e) {
            //Silently fail...(Please):
            System.out.println(e.toString());
        }
        return event;
    }

    public static void main(String[] args) throws MidiUnavailableException, InvalidMidiDataException {

        //Testing note playing
        SimpleMidiPlayer myPlayer = new SimpleMidiPlayer(1,8);
        myPlayer.setInstrument(1, 1);
        myPlayer.setInstrument(2, 8);

        myPlayer.addToTrack(1, 80,1);
        myPlayer.addToTrack(1, 90,10);
        myPlayer.addToTrack(1, 100,20);
        myPlayer.addToTrack(1, 60,30);

        myPlayer.addToTrack(2, 50,1);
        myPlayer.addToTrack(2, 40,10);
        myPlayer.addToTrack(2, 30,20);
        myPlayer.addToTrack(2, 20,30);

        //Play the track
        myPlayer.playTrack();
        //myPlayer.stopPlayback();

        while(true) {
            if (!myPlayer.sequencer.isRunning()) {
                myPlayer.sequencer.close();
                System.exit(1);
            }
        }
    }
}

