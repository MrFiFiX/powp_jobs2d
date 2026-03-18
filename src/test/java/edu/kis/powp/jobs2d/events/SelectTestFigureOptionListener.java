package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.adapter.AbstractDriverAdapter;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.magicpresets.FiguresJane;
import edu.kis.powp.jobs2d.magicpresets.FiguresJoe;

public class SelectTestFigureOptionListener implements ActionListener {

	private enum FigureUser {
		JOE1("Figure Joe 1", d -> FiguresJoe.figureScript1(d.getCurrentDriver())),
		JOE2("Figure Joe 2",d -> FiguresJoe.figureScript2(d.getCurrentDriver())),
		JANE("Figure Jane", d -> FiguresJane.figureScript(new AbstractDriverAdapter(0,0,d.getCurrentDriver())));

		String name;
		Consumer<DriverManager> consumer;

		public static FigureUser fromName(String name) {
			for (FigureUser f : values()) {
				if (f.name.equals(name)) {
					return f;
				}
			}
			return null;
		}

		private FigureUser(String s, Consumer<DriverManager> consumer){
			name = s;
			this.consumer = consumer;
		}
	}

	private DriverManager driverManager;

	public SelectTestFigureOptionListener(DriverManager driverManager) {
		this.driverManager = driverManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		FigureUser.fromName(e.getActionCommand()).consumer.accept(driverManager);
	}
}
