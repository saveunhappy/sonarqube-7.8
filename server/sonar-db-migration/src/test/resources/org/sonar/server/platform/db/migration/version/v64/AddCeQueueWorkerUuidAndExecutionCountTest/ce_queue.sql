CREATE TABLE "CE_QUEUE" (
  "ID" INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1),
  "UUID" VARCHAR(40) NOT NULL,
  "TASK_TYPE" VARCHAR(15) NOT NULL,
  "COMPONENT_UUID" VARCHAR(40) NULL,
  "STATUS" VARCHAR(15) NOT NULL,
  "SUBMITTER_LOGIN" VARCHAR(255) NULL,
  "STARTED_AT" BIGINT NULL,
  "CREATED_AT" BIGINT NOT NULL,
  "UPDATED_AT" BIGINT NOT NULL
);
CREATE UNIQUE INDEX "CE_QUEUE_UUID" ON "CE_QUEUE" ("UUID");
CREATE INDEX "CE_QUEUE_COMPONENT_UUID" ON "CE_QUEUE" ("COMPONENT_UUID");
CREATE INDEX "CE_QUEUE_STATUS" ON "CE_QUEUE" ("STATUS");
