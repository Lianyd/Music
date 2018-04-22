import javax.sound.midi.*;
//通过改变instrument改变乐器，通过改变note改变音调
public class MiniMusicCmdLine {
    public static void main(String[] args){
        MiniMusicCmdLine mini = new MiniMusicCmdLine();
            int instrument = 0;
            int note =40;
            mini.play(instrument,note);
    }

    public void play(int instrument, int note){
        try {
            Sequencer player = MidiSystem.getSequencer();  // CD播放器
            player.open();  // 打开播放器
            Sequence seq = new Sequence(Sequence.PPQ,4);  // CD
            Track track = seq.createTrack();  // 专辑

            MidiEvent event = null;

            ShortMessage first = new ShortMessage();
            first.setMessage(192,1,instrument,0);
            MidiEvent changeInstrument = new MidiEvent(first,1);
            track.add(changeInstrument);

            ShortMessage a = new ShortMessage();
            a.setMessage(144,1,note,100);
            MidiEvent noteOn = new MidiEvent(a,1);
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128,1,note,100);
            MidiEvent noteOff = new MidiEvent(b,16);
            track.add(noteOff);

            player.setSequence(seq);
            player.start();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}


