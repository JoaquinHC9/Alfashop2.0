import { defineConfig } from 'cypress';
import coverageTask from "@cypress/code-coverage/task";

export default defineConfig({
  component: {
    devServer: {
      framework: "react",
      bundler: "vite",
    },
  },

  e2e: {
    setupNodeEvents(on, config) {
      coverageTask(on, config);
      return config
    },
  },
});
