// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  apiEndpoint: 'http://localhost:8080/qsallcomponents-jee',
  production: false,
  versions: {
    core: '8.4.0',
    charts: '8.0.0',
    filemanager: '8.4.0',
    map: '8.0.1',
    theming: '8.0.1'
  }
};
