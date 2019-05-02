export interface PollForm {
  question: string
  id: number
  userChoice: boolean
  canVote: boolean,
  favorVoteCount: number,
  underdogVoteCount: number
}


export interface Poll {
  id: number,
  question: string,
  tsCreated: number,
  canVote: boolean,
  userChoice: boolean,
  favorVoteCount: number,
  underdogVoteCount: number
}
