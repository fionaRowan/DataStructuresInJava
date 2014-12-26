

  public class CharAndWeight{
		Character character;
		double weight=1;
		public String code;
		public CharAndWeight(Character x){
			character= x;
		}
		public CharAndWeight(Character x, double y){
			character=x;
			weight=y;
		}
		public CharAndWeight(Character car, String huffCode) {
			code=huffCode;
		}
		public boolean equals(CharAndWeight b){
			boolean isEqual;
			if (this.character.equals(b.character))
				isEqual= true;
			else
				isEqual=false;
			return isEqual;
		}
	}

