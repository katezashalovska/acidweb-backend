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
    'soulx',
    'SoulX',
    'Lifestyle & Wellness',
    'iOS & Android • Flutter, Firebase • Australia',
    'A mood-based content app in eight languages, including right-to-left layout, custom UX/UI, and cloud messaging.',
    'Flutter, Firebase, RTL & Multilingual, UX/UI',
    'https://apps.apple.com/de/app/soulx/id6753581623',
    NULL,
    true
),
(
    'gymsplat',
    'GymSplat',
    'Fitness & Workouts',
    'iOS & Android • React Native, Rive, Firebase • USA',
    'Fitness platform designed to help users plan workouts, follow training programs, and stay consistent with an integrated AI Coach.',
    'React Native, Rive, Firebase, AI Coach',
    NULL,
    NULL,
    true
),
(
    'the-great-project',
    'The Great Project',
    'Fitness & Health',
    'iOS & Android • Flutter, Java, Firebase • UK',
    'An AI Fitness Coach app combining goal-oriented workouts, personalized nutrition, and conversational AI coaching.',
    'Flutter, Java, Firebase, AI Coach',
    NULL,
    NULL,
    true
),
(
    'chumly',
    'Chumly',
    'Social & Geolocation',
    'iOS & Android • Flutter, Firebase • USA',
    'Meeting people nearby, now rather than next week. Built with Flutter, Firebase, and real-time instant messaging.',
    'Flutter, Firebase, Geolocation, Real-time Chat',
    'https://apps.apple.com/ua/app/chumly/id6755146348',
    NULL,
    true
),
(
    'taskbloom',
    'TaskBloom',
    'Productivity',
    'Android & iOS • Kotlin, Firebase • Germany',
    'TaskBloom simplifies task management with soft, pleasant design, custom micro-animations, and offline-first productivity tools.',
    'Kotlin, Firebase, Productivity, UX/UI',
    NULL,
    NULL,
    true
)
ON CONFLICT (slug) DO NOTHING;

-- Seed Testimonials
INSERT INTO testimonials (author_name, role, company, quote, metric, order_index)
VALUES
(
    'Peter Frank',
    'iOS App Founder',
    'iOS Analytics & Scaling',
    'Working with Kate and Michael was really great. Very friendly, capable, and responsive. I really enjoyed working with them and I''d highly recommend them if you need any help. I worked with Michael closely on setting up the Facebook SDK and CAPI properly to help ensure the data flow to my ad campaigns is clean and optimizable to scale my iOS mobile app.',
    '5★ Rating',
    1
),
(
    'Toghrul Aghayev',
    'Product Owner',
    'WebRTC App',
    'We worked with AcidSoft on implementing full voice and video call functionality in our mobile application using WebRTC. Their team handled the full development process of 1-1 audio and video calls. Despite technical complexity, they delivered a stable, functional solution within a short timeframe. Calls work reliably in production, and communication was proactive.',
    '5★ Rating',
    2
),
(
    'Sid J',
    'Founder',
    'HungerLink Foundation',
    'We had some important fixes to be made to our app and it had to be done quickly. Kate Zashalovska and Max worked over the weekend to ensure that the fixes were implemented and tested. They not only stayed up late to ensure all the work had been done, they also ensured proper testing. I really appreciate their professionalism and commitment. Totally recommended!',
    '5★ Rating',
    3
);

