# Welcome current date and time

## Goal

Show the device's current local date and time in `WelcomeContainer` using
`kotlinx-datetime`, shared across Android and iOS.

## Design

- Keep the existing version-catalog entry for `org.jetbrains.kotlinx:kotlinx-datetime`.
- Add that dependency to `commonMain`, rather than only to `androidMain`, because
  `WelcomeContainer` is shared Compose code.
- Read `Clock.System.now()` once during composition.
- Convert the resulting `Instant` with
  `TimeZone.currentSystemDefault()` and format it as `dd/MM/yyyy HH:mm`.
- Pass the formatted value to the existing `TextNormal` component.
- Do not introduce a ViewModel or timer: this change displays the current value
  when the welcome screen is composed and avoids unnecessary lifecycle/state
  complexity.

## Error handling and platform behavior

`kotlinx-datetime` provides the current instant and system time zone on each
supported target. No platform-specific `expect`/`actual` code or new
permissions are needed.

## Validation

Validate the shared module compilation and Android debug compilation. Confirm
that the date/time imports come from `kotlinx.datetime` and that no invalid
`kotlin.time` or placeholder API remains in `WelcomeContainer`.
