package beans;

/**
 * 
 * @author Abdelmalik GHOUBIR
 * @date 17 June 2019
 * 
 */

import java.io.Serializable;
import java.text.DecimalFormat;

import javax.swing.JLabel;

import utilies.Terminal;

public class ScoreResultBean extends JLabel implements Serializable {

	private static final long serialVersionUID = 1L;

	public ScoreResultBean() {
		super();
	}

	public String getScoreResult(String firstTemplate, String secondTemplate) {
		String executableMccMatcher = "./resources/exe\\MCCSdkV2.0\\SourceCode\\C#\\bin\\Debug\\MccMatcher.exe";
		String MccPaperMatchParameters = "./resources/exe\\MCCSdkV2.0\\Executables\\MccPaperMatchParameters.xml";
		Terminal.executeCommand(executableMccMatcher + " " + firstTemplate + " " + secondTemplate + " " + MccPaperMatchParameters + " scoreTemp.txt");
		String score = Terminal.executeCommand("awk '{printf $NF}' scoreTemp.txt");
		DecimalFormat df = new DecimalFormat("#.####");
		setText("Score = " + df.format(Double.parseDouble(score)));
		Terminal.executeCommand("rm scoreTemp.txt");
		return df.format(Double.parseDouble(score));
	}
}