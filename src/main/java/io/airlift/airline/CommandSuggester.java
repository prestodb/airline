package io.airlift.airline;

import com.google.common.collect.ImmutableList;
import io.airlift.airline.model.CommandMetadata;
import jakarta.inject.Inject;

public class CommandSuggester
        implements Suggester
{
    @Inject
    public CommandMetadata command;

    @Override
    public Iterable<String> suggest()
    {
        ImmutableList.Builder<String> suggestions = ImmutableList.<String>builder()
                .addAll(command.getCommandOptions().stream()
                        .flatMap(optionMetadata -> optionMetadata.getOptions().stream())
                        .iterator());

        if (command.getArguments() != null) {
            suggestions.add("--");
        }

        return suggestions.build();
    }
}
