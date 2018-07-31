CREATE TABLE action (
  id BIGSERIAL PRIMARY KEY,
  action_id UUID UNIQUE NOT NULL,
  action_type TEXT NOT NULL,
  arguments_data TEXT,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  committed_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE event (
  id BIGSERIAL PRIMARY KEY,
  event_id UUID UNIQUE NOT NULL,
  event_type TEXT NOT NULL,
  data_value TEXT,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);
