package io.airlift.airline;

import io.airlift.airline.model.CommandGroupMetadata;
import io.airlift.airline.model.CommandMetadata;
import jakarta.inject.Inject;

import static com.google.common.collect.Streams.concat;

public class GroupSuggester
        implements Suggester
{
    @Inject
    public CommandGroupMetadata group;

    @Override
    public Iterable<String> suggest()
    {
        return () -> concat(
                group.getCommands().stream().map(CommandMetadata::getName),
                group.getOptions().stream().flatMap(optionMetadata -> optionMetadata.getOptions().stream())).iterator();
    }
}
