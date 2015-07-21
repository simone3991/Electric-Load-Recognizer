package it.rhai.simulation.abstraction;

import it.bmi.jtsa.test.JTSATester;
import it.distanciable.sequences.Sequence;
import it.rhai.model.PowerConsumptionLabel;
import it.rhai.settings.SettingsKeeper;
import it.rhai.util.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * This implementation of {@link Abstractor} interface uses the JTSA Framework
 * to extract a sequence of elementary patterns from a data file
 * 
 * @author simone
 *
 */
public class JTSAAbstractor implements Abstractor<PowerConsumptionLabel> {

	private Abstractor<PowerConsumptionLabel> outputAbstractor;

	public JTSAAbstractor(Abstractor<PowerConsumptionLabel> outputAbstractor) {
		this.outputAbstractor = outputAbstractor;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see it.rhai.identification.Sequencer#buildSequence(java.io.File)
	 */
	public Sequence<PowerConsumptionLabel> buildSequence(File data)
			throws IOException {
		try {
			File xmlModified = FileUtils.replaceLabel(data.getPath(), "$TMP",
					new File("data/template.xml"));
			xmlModified.deleteOnExit();
			JTSATester.run(xmlModified.getName());
		} catch (Exception e) {
			SettingsKeeper.getSettings().getDebugLogger()
					.handle(e.getMessage());
		}
		File tmpFile = new File("data/output.jtsa");
		tmpFile.deleteOnExit();
		return outputAbstractor.buildSequence(tmpFile);
	}
}
