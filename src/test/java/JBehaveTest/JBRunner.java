package JBehaveTest;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.Collections;
import java.util.List;

public class JBRunner extends JUnitStories {
    // Specify a class with step definitions
    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new JBehaveSteps());
    }

    // Configure the test runner
    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                // Configure the story report
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        // Post the report to the console
                        .withFormats(Format.CONSOLE));
    }

    // Specify a feature file
    protected List<String> storyPaths() {
        return Collections.singletonList("sample.story");
    }
}
