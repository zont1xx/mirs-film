package com.mirsfilm;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ModularityTests {

	// Fails the build when one module reaches into another module's internal packages.
	@Test
	void verifiesModuleBoundaries() {
		ApplicationModules.of(BackendApplication.class).verify();
	}

}
