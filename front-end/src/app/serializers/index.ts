import {Poll} from "../model/model.poll";

export const serializePollsToPollsForm = (polls: Poll[]) => polls.map(poll => ({
  id: poll.id,
  question: poll.question,
  userChoice: poll.userChoice,
  canVote: poll.canVote,
  favorVoteCount: poll.favorVoteCount,
  underdogVoteCount: poll.underdogVoteCount
}))
