package com.innotech.votingsim;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.innotech.votingsim.utilities.LayoutManager;

@SpringBootApplication
public class VotingSimApplication implements CommandLineRunner {
	public static final LayoutManager uiManager = new LayoutManager();

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
		uiManager.buildGUI();
	}
}
