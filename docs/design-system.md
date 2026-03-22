# Design System

## Overview

This document defines the visual foundations and UI conventions for the catering platform.

The current design direction is:

- warm, refined, and editorial
- clean and modern without looking generic
- focused on clarity, trust, and easy form completion
- visually aligned with hospitality and event services

This file should be used as the baseline reference for new screens, component updates, and future UI refinements.

## Foundations

### Color Palette

#### Brand and Core Colors

- Primary: `#2F4A3D`
- Secondary: `#6B5C53`
- Accent: `#C57A47`

#### Neutral Colors

- Neutral 0: `#FFFDF9`
- Neutral 50: `#F7F2EA`
- Neutral 100: `#EFE6D9`
- Neutral 200: `#DCCFBF`
- Neutral 400: `#917F70`
- Neutral 700: `#3B342E`

#### Feedback Colors

- Success: `#2E7D5B`
- Warning: `#B9852F`
- Error: `#B65252`

### Color Usage

- `Primary` should be used for the main brand presence, strong headings, key actions, and important emphasis.
- `Secondary` should be used for supporting text, descriptive copy, and lower-priority information.
- `Accent` should be used sparingly for highlights, section markers, and secondary emphasis.
- `Neutral` colors should define the page background, surfaces, borders, and text hierarchy.
- `Success`, `Warning`, and `Error` should be used only for states, feedback, and validation.

### Typography

#### Font Families

- Display and headings: `Cormorant Garamond`
- Body and interface text: `Instrument Sans`

#### Typography Hierarchy

- H1: `clamp(2.9rem, 4.8vw, 3.9rem)`
- H2: `clamp(2rem, 4vw, 3.2rem)`
- H3: `2rem`
- Body default: `1rem`
- Small support text: `0.88rem`
- Eyebrow and meta text: `0.78rem`

### Typography Guidelines

- Use `Cormorant Garamond` only for display hierarchy and titles.
- Use `Instrument Sans` for body copy, labels, buttons, form fields, and helper text.
- Prefer short, direct text in headings.
- Keep support copy natural and functional, not overly promotional.

### Spacing

#### Spacing Scale

- `--space-2xs`: `0.375rem`
- `--space-xs`: `0.5rem`
- `--space-sm`: `0.75rem`
- `--space-md`: `1rem`
- `--space-lg`: `1.5rem`
- `--space-xl`: `2rem`
- `--space-2xl`: `3rem`
- `--space-3xl`: `4.5rem`

### Spacing Guidelines

- Use `md` to `lg` spacing between related fields.
- Use `xl` to `2xl` spacing between major sections.
- Preserve generous vertical rhythm in forms.
- Avoid stacking too many small gaps, which makes layouts feel noisy and inconsistent.

### Border Radius

- Small: `14px`
- Medium: `22px`
- Large: `32px`

### Border Radius Guidelines

- Small radius is for alerts and compact UI pieces.
- Medium radius is for inputs, upload zones, and grouped containers.
- Large radius is for hero panels and major page surfaces.

### Shadows

- Soft shadow: `0 24px 60px rgba(53, 42, 31, 0.12)`
- Card shadow: `0 18px 36px rgba(32, 24, 17, 0.08)`

### Shadow Guidelines

- Use soft shadows on main surfaces and large cards.
- Use card shadows on image cards and medium-elevation elements.
- Avoid layering many different shadow styles on the same screen.

## Design Tokens

The current source of truth for visual tokens is:

- [styles.css](/C:/Users/emman/OneDrive/Documentos/dev/catering.app/src/main/resources/static/css/styles.css)

Current token categories:

- colors
- spacing
- radius
- shadows

Recommended future token naming:

- `color-primary`
- `color-neutral-100`
- `space-lg`
- `radius-md`
- `shadow-card`

If the project evolves, these tokens should eventually move to a dedicated token layer shared across screens and components.

## Layout Principles

### Page Structure

The preferred page structure for form-heavy screens is:

1. Context or supporting hero panel
2. Main content container
3. Section-based form layout
4. Clear bottom action area

### Layout Rules

- Use sectioned forms instead of one long undivided form block.
- Group fields by user intention, not only by backend entity structure.
- Keep a clear reading path from top to bottom.
- Use two-column grids on desktop when it improves scanning.
- Collapse to one column on smaller screens.

## Component Guidelines

### Buttons

#### Primary Button

- Pill-shaped
- Strong filled background using primary color
- Uppercase text allowed only for strong final actions
- Used for the main action of the page

#### Secondary Button

- Softer background with accent or low-contrast neutral tone
- Used for add/remove helper actions

### Button Rules

- One dominant primary action per screen
- Avoid multiple competing high-emphasis buttons
- Motion should be subtle and responsive, not decorative

### Inputs

#### Input Style

- Light warm background
- Medium-large radius
- Soft border
- Visible focus state with subtle ring
- Muted placeholder text

### Input Rules

- Labels must always be visible
- Helper text should exist only when it prevents mistakes
- Validation should be clear and close to the field
- Avoid relying only on placeholder text to explain input meaning

### Cards and Panels

- Main content should live inside soft elevated panels
- Section panels should separate topics clearly
- Use light surfaces and fine borders instead of heavy contrast boxes

### Modals

Recommended modal style:

- neutral light surface
- large heading with body-font support text
- one strong primary action
- one low-emphasis secondary action
- no crowded footer

If modals are added later, they should follow the same tokens and spacing patterns already defined here.

### Upload Areas

- Use dashed borders with a soft surface
- Keep the call to action obvious
- Show uploaded previews clearly and consistently
- Avoid making upload states visually noisy

## Content and Microcopy

### Tone of Voice

- direct
- clear
- professional
- human

### Copy Guidelines

- Prefer practical descriptions over marketing language
- Avoid exaggerated claims for basic functionality
- Keep helper text short and useful
- Use PT-BR with proper accents in user-facing copy

Examples:

- Good: `Organize os principais canais de atendimento.`
- Avoid: `Uma experiência revolucionária para gestão inteligente de contatos.`

## Accessibility and Usability

- Maintain sufficient contrast between text and surfaces
- Preserve visible focus styles
- Avoid overly small text in form-heavy screens
- Use consistent spacing to make forms easier to scan
- Keep sections clearly titled
- Do not depend on color alone to communicate validation or status

## Responsive Behavior

- Desktop: two-column layouts when useful
- Tablet: hero may stack above content
- Mobile: single-column form layout

Responsive priorities:

- preserve readability first
- preserve form usability second
- preserve decorative treatment third

## Current Screen Pattern

The current `/events/create` and `/events/edit` screens follow this structure:

1. Left hero panel with context and guidance
2. Right main form shell
3. Form header with title and support text
4. Section blocks:
   - supplier profile
   - contact
   - address
   - gallery
5. Bottom action area

This pattern should be reused for similar administrative or form-based flows unless there is a strong reason to change it.

## Future Evolution

Recommended next steps for the design system:

- document reusable component variants
- add examples with screenshots or mock references
- define table, navigation, empty state, and modal patterns
- centralize tokens in a more explicit structure
- create a PT-BR microcopy guideline for labels, buttons, and validation messages

## Source Files

The current design system is implemented in:

- [styles.css](/C:/Users/emman/OneDrive/Documentos/dev/catering.app/src/main/resources/static/css/styles.css)
- [eventProviderCreateForm.jsp](/C:/Users/emman/OneDrive/Documentos/dev/catering.app/src/main/webapp/WEB-INF/views/eventProvider/eventProviderCreateForm.jsp)
- [eventProviderEditForm.jsp](/C:/Users/emman/OneDrive/Documentos/dev/catering.app/src/main/webapp/WEB-INF/views/eventProvider/eventProviderEditForm.jsp)
- [eventProviderForm.tag](/C:/Users/emman/OneDrive/Documentos/dev/catering.app/src/main/webapp/WEB-INF/tags/eventProvider/eventProviderForm.tag)
