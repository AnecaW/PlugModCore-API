package org.wannes.plugModCore.api;

import java.util.List;

/** Rendered text and request-specific diagnostics, without references to provider implementations. */
public record PlaceholderResult(String text, List<String> diagnostics) {
    public PlaceholderResult { diagnostics = List.copyOf(diagnostics); }
}
