export const API_URL = "http://localhost:8080";
export const CREATE_POLL_URL = API_URL+'/api/polls/new';
export const SEND_VOTE_URL = (id: string) => API_URL+'/api/polls/'+id;
export const GET_POLLS_URL = API_URL+'/api/polls';
