CREATE TABLE media_files (
    id UUID PRIMARY KEY,
    content BYTEA NOT NULL,
    content_type VARCHAR(100) NOT NULL,
    original_filename VARCHAR(255) NOT NULL,
    size_bytes BIGINT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE portfolio_cases
    ADD COLUMN image_id UUID REFERENCES media_files(id) ON DELETE SET NULL,
    ADD COLUMN image_alt VARCHAR(255),
    ADD COLUMN problem TEXT,
    ADD COLUMN what_we_built TEXT,
    ADD COLUMN hard_part TEXT,
    ADD COLUMN behance_url VARCHAR(500);

CREATE INDEX idx_portfolio_cases_image_id ON portfolio_cases(image_id);
