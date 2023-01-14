package edu.kis.powp.jobs2d.drivers;

public interface Visitor {
    public void visitCommandsRecorderDriver(CommandsRecorderDriver commandsRecorderDriver);
    public void visitDriverManager(DriverManager driverManager);
    public void visitMaintainRecordingAfterDriverChangeObserver(MaintainRecordingAfterDriverChangeObserver maintainRecordingAfterDriverChangeObserver);
    public void visitRecordingManager(RecordingManager recordingManager);
    public void visitSelectDriverMenuOptionListener(SelectDriverMenuOptionListener selectDriverMenuOptionListener);
}
