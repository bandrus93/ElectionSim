package com.innotech.votingsim;

import com.innotech.votingsim.producers.CandidateControllerFactory;
import com.innotech.votingsim.producers.ElectionControllerFactory;
import com.innotech.votingsim.producers.PopulationControllerFactory;
import com.innotech.votingsim.producers.GuiBuilder;
import com.innotech.votingsim.views.GuiView;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class VotingSimApplication implements CommandLineRunner {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(VotingSimApplication.class)
				.web(WebApplicationType.NONE)
				.headless(false)
				.bannerMode(Banner.Mode.OFF)
				.run();
	}

	@Override
	public void run(String... args) {
		GuiBuilder.Gui gui = new GuiBuilder()
				.addInput(PopulationControllerFactory.getInstance())
				.addInput(CandidateControllerFactory.getInstance())
				.addInput(ElectionControllerFactory.getInstance())
				.addView(new GuiView())
				.build();
		gui.getGuiView().getContentWindow().setVisible(true);
	}
}
