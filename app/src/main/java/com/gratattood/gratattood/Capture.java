package com.gratattood.gratattood;

/**
 * Created by NabilurRehman on 9/15/2017.
 */

public class Capture  {
//    TargetDataLine line;
//    Thread thread;
//
//    public void start() {
//        errStr = null;
//        thread = new Thread(this);
//        thread.setName("Capture");
//        thread.start();
//    }
//
//    public void stop() {
//        thread = null;
//    }
//
//    private void shutDown(String message) {
//        if ((errStr = message) != null && thread != null) {
//            thread = null;
//            samplingGraph.stop();
//            System.err.println(errStr);
//        }
//    }
//
//    public void run() {
//
//        duration = 0;
//        audioInputStream = null;
//
//        // define the required attributes for our line,
//        // and make sure a compatible line is supported.
//
//        AudioFormat format = audioInputStream.getFormat();
//        DataLine.Info info = new DataLine.Info(TargetDataLine.class,
//                format);
//
//        if (!AudioSystem.isLineSupported(info)) {
//            shutDown("Line matching " + info + " not supported.");
//            return;
//        }
//
//        // get and open the target data line for capture.
//
//        try {
//            line = (TargetDataLine) AudioSystem.getLine(info);
//            line.open(format, line.getBufferSize());
//        } catch (LineUnavailableException ex) {
//            shutDown("Unable to open the line: " + ex);
//            return;
//        } catch (SecurityException ex) {
//            shutDown(ex.toString());
//            JavaSound.showInfoDialog();
//            return;
//        } catch (Exception ex) {
//            shutDown(ex.toString());
//            return;
//        }
//
//        // play back the captured audio data
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        int frameSizeInBytes = format.getFrameSize();
//        int bufferLengthInFrames = line.getBufferSize() / 8;
//        int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
//        byte[] data = new byte[bufferLengthInBytes];
//        int numBytesRead;
//
//        line.start();
//
//        while (thread != null) {
//            if((numBytesRead = line.read(data, 0, bufferLengthInBytes)) == -1) {
//                break;
//            }
//            out.write(data, 0, numBytesRead);
//        }
//
//        // we reached the end of the stream.  stop and close the line.
//        line.stop();
//        line.close();
//        line = null;
//
//        // stop and close the output stream
//        try {
//            out.flush();
//            out.close();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//        // load bytes into the audio input stream for playback
//
//        byte audioBytes[] = out.toByteArray();
//        ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
//        audioInputStream = new AudioInputStream(bais, format, audioBytes.length / frameSizeInBytes);
//
//        long milliseconds = (long)((audioInputStream.getFrameLength() * 1000) / format.getFrameRate());
//        duration = milliseconds / 1000.0;
//
//        try {
//            audioInputStream.reset();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return;
//        }
//
//        samplingGraph.createWaveForm(audioBytes);
//    }
//} // End class Capture
//
//    public static void main(String [] args) throws Exception {
//        if (args.length != 2) {
//            printUsage();
//            System.exit(1);
//        }
//        AudioWaveformCreator awc = new AudioWaveformCreator(args[0], args[1]);
//        awc.createAudioInputStream();
//    }
//
//    private void reportStatus(String msg) {
//        if ((errStr = msg) != null) {
//            System.out.println(errStr);
//        }
//    }
//
//    private static void printUsage() {
//        System.out.println("AudioWaveformCreator usage: java AudioWaveformCreator.class [path to audio file for generating the image] [path to save waveform image to]");
//    }
}
