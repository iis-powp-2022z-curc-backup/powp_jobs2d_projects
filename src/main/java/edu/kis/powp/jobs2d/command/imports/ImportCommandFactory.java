package edu.kis.powp.jobs2d.command.imports;

public class ImportCommandFactory {

    public ImportCommandInterface importer(String extension) throws Exception {
        FileExtensions fExtension = FileExtensions.valueOf(extension);
        switch (fExtension) {
            case txt: return new ImportTxtCommand();
            case json: return new ImportJSONCommand();
            default: throw new Exception("Incorrect file extension: " + extension);
        }
    }
}
