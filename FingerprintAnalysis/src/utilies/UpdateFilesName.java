package utilies;

import java.io.File;

enum Finger {
    LeftPinkFinger, LeftRingFinger, LeftMiddleFinger, LeftIndexFinger, LeftThumb,
    RightPinkFinger, RightRingFinger, RightMiddleFinger, RightIndexFinger, RightThumb;
}

public class UpdateFilesName {
    public static void main(String[] args) throws Exception {
        String foldersPathString = args[0];
        File[] listOfFiles = new File(foldersPathString).listFiles();
        for (File file: listOfFiles)
            if (file.isDirectory())
                (new UpdateFilesName()).changeFolderFilesNames(file.getAbsolutePath());
    }

    public void changeFolderFilesNames(String folderPathString) throws Exception {
        File[] listOfFiles = new File(folderPathString).listFiles();

        for (File file: listOfFiles) {
            if (file.isFile()) {
                String[] strings = file.getName().split("_");

                if (!file.getName().endsWith(".png")) {
                    file.delete();
                } else if (strings.length > 2) {
                    if (strings[2].equalsIgnoreCase("Enrolment"))
                        file.delete();
                    else if (strings[2].equalsIgnoreCase("Authentication")){
                        strings[3] = "I" + (Finger.valueOf(strings[3]).ordinal() + 1);
                        strings[4] = "S" + strings[4];
                        String newfilePath = folderPathString + "\\" + strings[3] + "_" + strings[4] + ".png";
                        file.renameTo(new File(newfilePath));
                    }
                }
            }
        }
    }
}