package io.airlift.airline;

import io.airlift.airline.model.CommandGroupMetadata;
import io.airlift.airline.model.CommandMetadata;
import io.airlift.airline.model.GlobalMetadata;
import jakarta.inject.Inject;

import static com.google.common.collect.Streams.concat;

public class GlobalSuggester
        implements Suggester
{
    @Inject
    public GlobalMetadata metadata;

    @Override
    public Iterable<String> suggest()
    {
        return () -> concat(
                concat(
                        metadata.getCommandGroups().stream().map(CommandGroupMetadata::getName),
                        metadata.getDefaultGroupCommands().stream().map(CommandMetadata::getName)),
                metadata.getOptions().stream().flatMap(option -> option.getOptions().stream())).iterator();
    }
}
