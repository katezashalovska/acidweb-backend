-- V2__seed_initial_data.sql
-- Seed initial public content for AcidSoft backend

-- Seed Blog Posts

INSERT INTO blog_posts (slug, title, category, summary, content, author, published_date)
VALUES 
(
    'flutter-vs-native-mobile-architecture',
    'Flutter vs Native iOS/Android: How We Architect Apps for Scale',
    'Architecture',
    'An in-depth breakdown of when cross-platform Flutter outperforms native code, and how to structure native bridges for device hardware.',
    'When building mobile products for growing businesses, one of the most critical engineering decisions is choosing between Native (Swift/Kotlin) and Flutter cross-platform development.

### The Myth of Performance Overhead
Modern Flutter applications compile directly to native ARM machine code using Darts AOT compiler. Impeller, Flutters rendering engine, eliminates shader compilation jank on both iOS and Android.

### When We Recommend Flutter
1. Unified Design Systems
2. Speed to Market
3. Budget Efficiency

### When Native Code Is Required
For specialized hardware integrations (Bluetooth LE, AR/VR, location tracking), we use Flutters platform channels to write native Swift and Kotlin modules.',
    'AcidSoft Engineering Team',
    'September 15, 2026'
),
(
    'code-takeover-auditing-legacy-mobile-apps',
    'Code Takeover Checklist: How to Safely Audit Legacy Mobile Apps',
    'Engineering',
    'What we look for when taking over an existing codebase from a previous developer or agency before writing new features.',
    'Taking over an existing iOS or Android application requires a disciplined audit process to identify crash risks, outdated dependencies, and architectural debt before adding new functionality.

### 1. Dependency Health & Store Compliance
- Third-party SDK privacy manifests
- Target SDK versions for Google Play

### 2. State Management & Architecture
- Clean separation of UI and state

### 3. CI/CD & Automated Release Pipelines
- Automated TestFlight and Google Play Internal Track distribution.',
    'AcidSoft Engineering Team',
    'August 28, 2026'
),
(
    'app-store-optimization-creative-conversion-hacks',
    'ASO Creatives: Why Screenshots & Video Previews Drive Downloads',
    'ASO & Growth',
    'Technical and visual strategies for optimizing App Store & Google Play product pages to increase impression-to-install rates.',
    'App Store Optimization (ASO) is fundamentally about visual conversion rate optimization.

### Visual Hierarchy on Mobile Stores
- First 3 Screenshots: 80% of store visitors never scroll past the third screenshot.
- Micro-Animations & Video: Including a 15-second portrait app preview video can increase download conversion rates by up to 24%.',
    'AcidSoft Product Team',
    'August 10, 2026'
)
ON CONFLICT (slug) DO NOTHING;

-- Seed Career Vacancies
INSERT INTO career_vacancies (title, department, job_type, description, is_active)
VALUES
(
    'Senior Flutter Engineer',
    'Engineering',
    'Full-time • Remote (Ukraine / EU)',
    'Lead the architecture of cross-platform iOS and Android applications, build custom native plugins, and integrate complex hardware/REST backend APIs.',
    true
),
(
    'Mobile Tech Lead (Native Swift / Kotlin)',
    'Engineering',
    'Full-time • Remote (Ukraine)',
    'Audit client codebases, architect native iOS/Android modules, establish automated CI/CD release pipelines (Fastlane), and oversee app store submissions.',
    true
),
(
    'UI/UX Product Designer',
    'Design',
    'Full-time / Contract • Remote',
    'Design intuitive mobile app interfaces, clickable Figma prototypes, and conversion-optimized App Store screenshot sets.',
    true
),
(
    'QA & App Store Compliance Specialist',
    'Quality Assurance',
    'Part-time • Remote',
    'Conduct automated & manual sprint testing, review store privacy manifests, and ensure strict compliance with Apple & Google review guidelines.',
    true
);

-- Seed Portfolio Cases
INSERT INTO portfolio_cases (slug, title, category, metric, description, tech_stack, app_store_url, google_play_url, is_featured)
VALUES
(
    'smooth-trailering',
    'Smooth Trailering',
    'IoT / Automotive',
    '4.9★ Store Rating • 50k+ Users',
    'Smart sensor telemetry app for real-time trailer towing safety, weight distribution monitoring, and tire pressure diagnostics.',
    'Flutter, Bluetooth LE Native Modules, AWS IoT Core, WebSockets',
    'https://apps.apple.com/',
    'https://play.google.com/',
    true
),
(
    'soulx',
    'SoulX',
    'Wellness & Audio',
    '100k+ Downloads • 4.8★ App Store',
    'Immersive spatial audio & meditation app featuring spatial soundscapes, offline sync, and personalized daily recommendations.',
    'Native Swift / AVFoundation, Kotlin Native, Firebase, WebGL',
    'https://apps.apple.com/',
    'https://play.google.com/',
    true
),
(
    'bitebudget',
    'BiteBudget',
    'FinTech / Consumer',
    '35% Reduction in Grocery Waste',
    'Smart grocery budget tracker with OCR receipt scanning, meal planning, and automated spending analytics.',
    'Flutter, Google Cloud Vision OCR, PostgreSQL, Node.js Backend',
    'https://apps.apple.com/',
    'https://play.google.com/',
    true
),
(
    'chumly',
    'Chumly',
    'Social / Micro-Events',
    '20k+ Monthly Active Creators',
    'Hyper-local social discovery app connecting people for real-time micro-events, meetups, and local community activities.',
    'React Native, Mapbox GL, Firebase Realtime DB, Node.js',
    'https://apps.apple.com/',
    'https://play.google.com/',
    true
),
(
    'bonnie-app',
    'The Bonnie App',
    'Health & Pet Care',
    '15k Active Pet Owners',
    'Comprehensive pet health records, vet appointment booking, and medication reminder system.',
    'Flutter, Firebase, Stripe API, Push Notifications',
    'https://apps.apple.com/',
    'https://play.google.com/',
    true
),
(
    'taxi-go-now',
    'Taxi Go Now',
    'Logistics & Mobility',
    '99.9% Uptime • 1M+ Completed Rides',
    'High-concurrency ride hailing and driver dispatch platform with live GPS tracking and dynamic surge pricing.',
    'Native Android/iOS, WebSockets, Redis, Microservices Backend',
    'https://apps.apple.com/',
    'https://play.google.com/',
    true
);

-- Seed Testimonials
INSERT INTO testimonials (author_name, role, company, quote, metric, order_index)
VALUES
(
    'Alex Vance',
    'CTO',
    'Smooth Trailering',
    'AcidSoft delivered our iOS and Android IoT app ahead of schedule with zero Bluetooth connectivity glitches. Their post-launch support keeps our app top-rated.',
    '4.9★ App Rating',
    1
),
(
    'Elena Rostova',
    'Founder & Head of Product',
    'SoulX Wellness',
    'Working with AcidSoft felt like extending our internal core team. They handled our spatial audio pipeline and store compliance seamlessly.',
    '100k+ Downloads',
    2
),
(
    'David Miller',
    'VP of Product',
    'BiteBudget',
    'The team audited our inherited codebase, cleaned up technical debt, and implemented automated CI/CD releases in less than 3 weeks.',
    '3-Week Turnaround',
    3
);
