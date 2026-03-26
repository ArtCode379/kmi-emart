# CLAUDE.md — Project Rules

## Efficiency Rules (MANDATORY)

- **DO NOT explore the project.** The full structure map is below. Use it instead of ls/find/cat.
- **Batch writes:** Plan ALL changes to a file, then write the ENTIRE file in ONE Write command.
- **No re-reading:** After you write a file, do NOT read it back to "verify". Trust your output.
- **Silent execution:** Do not explain steps. Just execute.
- **Parallel downloads:** Batch all curl/wget image downloads into one shell script and run it once.
- **Write, don't edit:** Use Write (full file replacement), not Edit (partial patches).
- **No verification loops:** Do not grep for "TODO" or "placeholder" after writing.

## Content Rules

- ALL text in English — UI, strings.xml, comments, identifiers, logs, README
- No Lorem ipsum, no placeholders — real meaningful content only
- Stock images from Unsplash/Pexels only (not AI-generated)

## Project Structure (DO NOT explore — use this map)

Package path after scaffold: kmiemart/consult/app

### Files to MODIFY:
- `data/repository/ServiceRepository.kt` — empty services list → fill with real ServiceModel objects
- `data/model/ServiceModel.kt` — data class → verify fields match your content
- `ui/composable/screen/home/HomeScreen.kt` — home layout → hero carousel, service categories, cards
- `ui/composable/screen/onboarding/OnboardingScreen.kt` — 3 slides → fill content
- `ui/composable/screen/splash/SplashScreen.kt` — splash → gradient + logo animation
- `ui/composable/screen/servicedetails/ServiceDetailsScreen.kt` — detail view → polish
- `ui/composable/screen/bookings/BookingsScreen.kt` — bookings list → polish
- `ui/composable/screen/checkout/CheckoutScreen.kt` — booking form → polish
- `ui/composable/screen/settings/SettingsScreen.kt` — settings → polish
- `ui/theme/Color.kt` — brand colors
- `ui/theme/Theme.kt` — rename Skeleton refs, apply brand
- `ui/theme/Type.kt` — typography
- `ServiceApp.kt` — Application class → rename to KMSTMApp
- `res/values/strings.xml` — app_name and UI strings
- `res/drawable/` — stock images

### Files to NOT modify:
- `MainActivity.kt`
- `data/dao/BookingDao.kt` — Room DAO
- `data/database/KMSTMDatabase.kt` — Room DB
- `data/database/converter/Converters.kt`
- `data/datastore/KMSTMOnboardingPrefs.kt`
- `data/entity/BookingEntity.kt`
- `data/repository/BookingRepository.kt`
- `data/repository/KMSTMOnboardingRepo.kt`
- `di/*` — Koin DI modules
- `ui/composable/approot/*`
- `ui/composable/navigation/*`
- `ui/composable/screen/checkout/CheckoutDialog.kt`
- `ui/composable/shared/*` — KMSTMContentWrapper, KMSTMEmptyView
- `ui/state/*`
- `ui/viewmodel/*`

---

## App Specification

**App:** KMI E-Mart
**Company:** KMI E MART LTD
**Domain:** IT consultancy — cybersecurity, cloud solutions, business process optimization
**Website:** kmieemarrt.uk

## UI Specifications (per screen)

For each screen below, implement the layout, components, and visual style exactly as described.
The Style Guide defines the visual language — apply it consistently across ALL screens.

### Style Guide (apply to all screens):

Visual mood: dark tech premium with glassmorphism accents
Color palette:
  - Primary: #4A90E2 — used for buttons, active states, key accents
  - Secondary/Accent: #667EEA — used for highlights, tags, gradient start
  - Background: #1A1F2E — main background color (dark)
  - Surface: #2D3748 — cards, elevated containers
  - On-primary: #FFFFFF — text/icons on primary color
  - On-surface: #E2E8F0 — primary text color (light on dark)
  - Muted text: #718096 — secondary/tertiary text
  - Divider/border: #3D4A5C — subtle separators
  - Gradient: #667EEA → #764BA2 (hero elements, splash)
Corner radius: 16dp (cards), 24dp (buttons/pills)
Elevation style: subtle ambient shadows (0dp-4dp), glassmorphic card feel with semi-transparent backgrounds
Typography:
  - Headline: bold sans-serif (Roboto/system default), 800 weight for hero, 700 for section titles
  - Body: regular sans-serif, 400 weight
  - Accent text: semibold uppercase 12sp tracking 1.2
Button style: gradient pill (#667EEA → #764BA2), rounded-full 48dp height, white text, subtle shadow
Card style: semi-transparent dark (Surface with 0.8 alpha), 1dp accent border (#4A90E2 at 15% opacity), rounded-16dp, subtle hover shadow
Spacing: balanced 16dp gaps, 24dp section spacing, generous padding
Icon style: filled rounded with gradient tint background, 15dp corner radius
Image treatment: rounded 12dp corners, ContentScale.Crop, subtle gradient overlay for hero images
Overall feel: Modern premium tech consultancy — dark, sophisticated, with blue-purple gradient accents. Glassmorphic elements create depth. Professional but cutting-edge.

### Screen: SplashScreen

Full-screen gradient background (#667EEA → #764BA2 diagonal). Centered app icon (icon.png) with fade-in + scale animation (0.8→1.0 over 800ms). App name "KMI E-Mart" below icon in white, fading in 200ms after icon. Auto-navigate after 1.5s to onboarding (first launch) or main screen. Use dark status bar.

### Screen: OnboardingScreen

3 slides with HorizontalPager + dot indicators.

Slide 1: "Smart IT Solutions" — "Access expert cybersecurity audits, cloud architecture, and digital transformation strategies from one unified platform." — image: technology consulting office
Slide 2: "Book Expert Consultations" — "Schedule sessions with certified IT professionals. From initial audits to full-scale deployments, we have you covered." — image: business meeting technology
Slide 3: "Stay Ahead of the Curve" — "Explore our knowledge base with the latest insights on cybersecurity trends, cloud innovations, and business optimization." — image: futuristic tech dashboard

Dark background, light text. Dot indicators: active = Primary (#4A90E2), inactive = Muted at 30%. "Get Started" button with gradient pill style on last slide. Save flag to DataStore.

### Screen: HomeScreen (Service)

Top bar: "KMI E-Mart" left, dark background, light text.
Hero carousel (HorizontalPager, 3 banners): featured services with full-width images, dark gradient overlay at bottom for text readability, dot indicators.
- Banner 1: "Cybersecurity Consulting" — "Protect your digital assets"
- Banner 2: "Cloud Solutions" — "Scale your infrastructure"
- Banner 3: "Business Optimization" — "Streamline your operations"

Service categories row (LazyRow): icon + label cards with gradient tint background
- Cybersecurity, Cloud, Optimization, Strategy, Audit, Analytics

"Our Services" section: vertical list of service cards. Each card: image left (80dp, rounded 12dp), service name (headline weight), brief description (2 lines, muted), price indicator "From $X". Semi-transparent dark card style. All clickable → ServiceDetailsScreen.

### Screen: ServiceDetailsScreen

Back arrow at top. Hero image full width 280dp with gradient overlay. Service name (22sp bold, light), category chip (accent color). Price/duration row: "From $150 · 2h initial session". Detailed description (14sp, muted on dark). Features list (3-5 bullet items with icon + text). Bottom: "Book Consultation" gradient pill button full width.

### Screen: BookingsScreen

Top bar: "My Bookings". Dark background. Empty state: centered icon + "No bookings yet" + "Browse Services" gradient button. LazyColumn of booking cards: semi-transparent dark card, service name, formatted date, status chip (Confirmed=blue, Completed=green, Cancelled=red). Data from BookingRepository (Room).

### Screen: CheckoutScreen (Booking Form)

Top bar: "Book Consultation" + back arrow. Service summary card (dark surface, name + price, non-editable). Form fields with outlined style on dark: name, phone, email, preferred date, notes. Fields: #3D4A5C border, #E2E8F0 text, #718096 hint. "Confirm Booking" gradient pill button. Success dialog on completion → navigate to Bookings.

### Screen: SettingsScreen

Dark background. Grouped sections:
- About: company name "KMI E MART LTD", app version "1.0.0"
- Support: "Visit Website" → opens kmieemarrt.uk in browser
- Preferences: dark mode toggle (already dark by default), notifications toggle
Items: icon + label + trailing widget. Dividers in #3D4A5C. Toggles use Primary for active state.

## Services Data (for ServiceRepository.kt)

Fill with these 8 services:

1. id=1, name="Cybersecurity Consulting", description="Comprehensive security audits, penetration testing, and vulnerability assessments to protect your digital infrastructure from evolving threats.", price="From $200", duration="3h session", category="Cybersecurity", image=R.drawable.service_cyber
2. id=2, name="Cloud Architecture", description="Design and implement scalable cloud solutions on AWS, Azure, or GCP. Migration planning, cost optimization, and infrastructure as code.", price="From $250", duration="4h session", category="Cloud", image=R.drawable.service_cloud
3. id=3, name="Business Process Optimization", description="Analyze and streamline your workflows with data-driven strategies. Reduce operational costs and boost team productivity.", price="From $180", duration="2h session", category="Optimization", image=R.drawable.service_bpo
4. id=4, name="IT Strategy Development", description="Align your technology roadmap with business goals. Long-term planning, vendor evaluation, and digital transformation consulting.", price="From $300", duration="3h session", category="Strategy", image=R.drawable.service_strategy
5. id=5, name="Digital Systems Audit", description="Thorough assessment of your existing IT systems, software licenses, and infrastructure health. Get a clear picture of your tech landscape.", price="From $150", duration="2h session", category="Audit", image=R.drawable.service_audit
6. id=6, name="Data Analytics & Insights", description="Transform raw data into actionable business intelligence. Dashboard design, KPI tracking, and predictive analytics implementation.", price="From $220", duration="3h session", category="Analytics", image=R.drawable.service_analytics
7. id=7, name="Network Infrastructure", description="Design, deploy, and maintain robust network solutions. From office LANs to enterprise WAN architectures with 99.9% uptime guarantee.", price="From $280", duration="4h session", category="Cloud", image=R.drawable.service_network
8. id=8, name="Compliance & Governance", description="Navigate GDPR, ISO 27001, SOC 2, and industry-specific regulations. Risk assessment frameworks and compliance roadmaps.", price="From $200", duration="2h session", category="Audit", image=R.drawable.service_compliance

## Images to Download

Download ALL in one batch script to res/drawable/:
- service_cyber.jpg — cybersecurity dark office monitors
- service_cloud.jpg — cloud computing server room
- service_bpo.jpg — business team collaboration
- service_strategy.jpg — technology strategy planning
- service_audit.jpg — digital audit analysis
- service_analytics.jpg — data analytics dashboard
- service_network.jpg — network infrastructure cables
- service_compliance.jpg — compliance documentation
- onboard1.jpg — technology consulting office
- onboard2.jpg — business meeting with tech screens
- onboard3.jpg — futuristic tech dashboard
- hero1.jpg — cybersecurity banner dark
- hero2.jpg — cloud infrastructure modern
- hero3.jpg — business optimization meeting

Use Unsplash with ?w=800&h=600 for services/heroes, ?w=800 for onboarding.

## Final Steps

After all code is written and images downloaded:
1. `source ~/.bashrc`
2. `gh repo create ArtCode379/kmi-emart --public --source=. --remote=origin --push`
3. Run: `openclaw system event --text "Done: KMI E-Mart app completed" --mode now`
